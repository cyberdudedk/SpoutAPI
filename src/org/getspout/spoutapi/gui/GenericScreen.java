/*
 * This file is part of Spout API (http://wiki.getspout.org/).
 * 
 * Spout API is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Spout API is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.getspout.spoutapi.gui;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.packet.PacketWidget;
import org.getspout.spoutapi.packet.PacketWidgetRemove;
import org.getspout.spoutapi.player.SpoutPlayer;

public abstract class GenericScreen extends GenericWidget implements Screen{
	protected HashMap<Widget, Plugin> widgets = new HashMap<Widget, Plugin>();
	protected int playerId;
	protected boolean bg = true;
	public GenericScreen() {
		
	}
	
	@Override
	public int getVersion() {
		return super.getVersion() + 0;
	}
	
	public GenericScreen(int playerId) {
		this.playerId = playerId;
	}

	@Override
	public Widget[] getAttachedWidgets() {
		Widget[] list = new Widget[widgets.size()];
		widgets.keySet().toArray(list);
		return list;
	}

	@Override
	@Deprecated
	public Screen attachWidget(Widget widget) {
		return attachWidget(null, widget);
	}
	
	@Override
	public Screen attachWidget(Plugin plugin, Widget widget) {
		widgets.put(widget, plugin);
		widget.setPlugin(plugin);
		widget.setDirty(true);
		widget.setScreen(this);
		return this;
	}

	@Override
	public Screen removeWidget(Widget widget) {
		SpoutPlayer player = SpoutManager.getPlayerFromId(playerId);
		if (player != null && player.isSpoutCraftEnabled()) {
			if (widgets.containsKey(widget)) {
				widgets.remove(widget);
				if(!widget.getType().isServerOnly()) {
					SpoutManager.getPlayerFromId(playerId).sendPacket(new PacketWidgetRemove(widget, getId()));
				}
				widget.setScreen(null);
			}
		}
		return this;
	}
	
	@Override
	public Screen removeWidgets(Plugin p) {
		for (Widget i : getAttachedWidgets()) {
			if (widgets.get(i) != null && widgets.get(i).equals(p)) {
				removeWidget(i);
			}
		}
		return this;
	}
	
	@Override
	public boolean containsWidget(Widget widget) {
		return containsWidget(widget.getId());
	}
	
	@Override
	public boolean containsWidget(UUID id) {
		return getWidget(id) != null;
	}
	
	@Override
	public Widget getWidget(UUID id) {
		for (Widget w : widgets.keySet()) {
			if (w.getId().equals(id)) {
				return w;
			}
		}
		return null;
	}
	
	@Override
	public boolean updateWidget(Widget widget) {
		if (widgets.containsKey(widget)) {
			Plugin plugin = widgets.get(widget);
			widgets.remove(widget);
			widgets.put(widget, plugin);
			widget.setScreen(this);
			return true;
		}
		return false;
	}
	
	@Override
	public void onTick() {
		SpoutPlayer player = SpoutManager.getPlayerFromId(playerId);
		if (player != null && player.isSpoutCraftEnabled()) {
			for (Widget widget : widgets.keySet()) {
				widget.onTick();
				if (widget.isDirty()) {
					if(! widget.getType().isServerOnly()){
						player.sendPacket(new PacketWidget(widget, getId()));
					}
					widget.setDirty(false);
				}
			}
		}
	}
	
	@Override
	public Screen setBgVisible(boolean enable) {
		bg = enable;
		return this;
	}
	
	@Override
	public boolean isBgVisible() {
		return bg;
	}
	
	@Override
	public SpoutPlayer getPlayer() {
		return SpoutManager.getPlayerFromId(playerId);
	}
	
	@Override
	public int getNumBytes() {
		return super.getNumBytes() + 1;
	}
	
	@Override
	public void readData(DataInputStream input) throws IOException {
		super.readData(input);
		setBgVisible(input.readBoolean());
	}

	@Override
	public void writeData(DataOutputStream output) throws IOException {
		super.writeData(output);
		output.writeBoolean(isBgVisible());
	}
	
	@Override
	public void setDirty(boolean dirty) {
		super.setDirty(dirty);
		if (dirty) {
			for (Widget widget : getAttachedWidgets()){
				widget.setDirty(true);
			}
		}
	}
	
	@Override
	public Widget copy() {
		throw new UnsupportedOperationException("You can not create a copy of a screen");
	}
	
	@Override
	public Set<Widget> getAttachedWidgetsAsSet(boolean recursive) {
		Set<Widget> set = new HashSet<Widget>();
		for(Widget w:widgets.keySet()) {
			set.add(w);
			if(w instanceof Screen && recursive) {
				set.addAll(((Screen)w).getAttachedWidgetsAsSet(true));
			}
		}
		return set;
	}
}
