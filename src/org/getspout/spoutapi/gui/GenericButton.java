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

import org.getspout.spoutapi.event.screen.ButtonClickEvent;
import org.getspout.spoutapi.packet.PacketUtil;

public class GenericButton extends GenericControl implements Button {

	protected GenericLabel label = (GenericLabel) new GenericLabel().setAlign(WidgetAnchor.TOP_CENTER);
	protected String disabledText = "";
	protected Color hoverColor = new Color(1, 1, 0.627F);
	protected float scale = 1.0F;
	
	public GenericButton() {
		
	}
	
	@Override
	public int getVersion() {
		return super.getVersion() + 3;
	}
	
	public GenericButton(String text) {
		setText(text);
	}
	
	@Override
	public int getNumBytes() {
		return super.getNumBytes() + label.getNumBytes() + PacketUtil.getNumBytes(getDisabledText()) + 9;
	}

	@Override
	public void readData(DataInputStream input) throws IOException {
		super.readData(input);
		label.readData(input);
		setDisabledText(PacketUtil.readString(input));
		setHoverColor(PacketUtil.readColor(input));
		scale = input.readFloat();
	}

	@Override
	public void writeData(DataOutputStream output) throws IOException {
		super.writeData(output);
		label.writeData(output);
		PacketUtil.writeString(output, getDisabledText());
		PacketUtil.writeColor(output, getHoverColor());
		output.writeFloat(scale);
	}

	@Override
	public String getText() {
		return label.getText();
	}

	@Override
	public Button setText(String text) {
		label.setText(text);
		return this;
	}

	@Override
	public Color getTextColor() {
		return label.getTextColor();
	}

	@Override
	public Button setTextColor(Color color) {
		label.setTextColor(color);
		return this;
	}

	@Override
	public String getDisabledText() {
		return disabledText;
	}

	@Override
	public Button setDisabledText(String text) {
		disabledText = text;
		return this;
	}
	
	@Override
	public Color getHoverColor() {
		return hoverColor;
	}
	
	@Override
	public Button setHoverColor(Color color) {
		this.hoverColor = color;
		return this;
	}
	
	@Override
	public WidgetType getType() {
		return WidgetType.Button;
	}

	@Override
	public Button setAuto(boolean auto) {
		label.setAuto(auto);
		return this;
	}

	@Override
	public boolean isAuto() {
		return label.isAuto();
	}

	@Override
	public WidgetAnchor getAlign() {
		return label.getAlign();
	}

	@Override
	public Button setAlign(WidgetAnchor pos) {
		label.setAlign(pos);
		return this;
	}
	
	@Override
	public Button copy() {
		return ((Button)super.copy()).setDisabledText(getDisabledText()).setText(getText()).setAuto(isAuto()).setTextColor(getTextColor()).setHoverColor(getHoverColor());
	}

	@Override
	public void onButtonClick(ButtonClickEvent event) {
		this.callEvent(event);
	}

	@Override
	public boolean isResize() {
		return label.isResize();
	}

	@Override
	public Label setResize(boolean resize) {
		return label.setResize(resize);
	}

	@Override
	public Label doResize() {
		return label.doResize();
	}

	@Override
	public Label setScale(float scale) {
		this.scale = scale;
		return this;
	}

	@Override
	public float getScale() {
		return scale;
	}
}
