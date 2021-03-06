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

import org.getspout.spoutapi.event.screen.SliderDragEvent;

public class GenericSlider extends GenericControl implements Slider {
	protected Label label = new GenericLabel();
	protected float slider = 0.5f;
	public GenericSlider() {
		
	}
	
	@Override
	public int getVersion() {
		return super.getVersion() + 1;
	}
	
	@Override
	public int getNumBytes() {
		return super.getNumBytes() + 4 + label.getNumBytes();
	}

	@Override
	public void readData(DataInputStream input) throws IOException {
		super.readData(input);
		setSliderPosition(input.readFloat());
		label.readData(input);
	}

	@Override
	public void writeData(DataOutputStream output) throws IOException {
		super.writeData(output);
		output.writeFloat(getSliderPosition());
		label.writeData(output);
	}

	@Override
	public float getSliderPosition() {
		return slider;
	}

	@Override
	public Slider setSliderPosition(float value) {
		if (value > 1f) {
			value = 1f;
		} else if (value < 0f) {
			value = 0f;
		}
		slider = value;
		return this;
	}
	
	@Override
	public WidgetType getType() {
		return WidgetType.Slider;
	}

	@Override
	public Slider copy() {
		return ((Slider)super.copy()).setSliderPosition(getSliderPosition());
	}

	@Override
	public void onSliderDrag(SliderDragEvent event) {
		this.callEvent(event);
	}

	@Override
	public String getText() {
		return label.getText();
	}

	@Override
	public Color getTextColor() {
		return label.getTextColor();
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
	public Label setScale(float scale) {
		label.setScale(scale);
		return this;
	}

	@Override
	public float getScale() {
		return label.getScale();
	}

	@Override
	public Slider setText(String text) {
		label.setText(text);
		return this;
	}

	@Override
	public Slider setTextColor(Color color) {
		label.setTextColor(color);
		return this;
	}

	@Override
	public Slider setAuto(boolean auto) {
		label.setAuto(auto);
		return this;
	}

	@Override
	public Slider setAlign(WidgetAnchor align) {
		label.setAlign(align);
		return this;
	}

	@Override
	public boolean isResize() {
		return label.isResize();
	}

	@Override
	public Label setResize(boolean resize) {
		label.setResize(resize);
		return this;
	}

	@Override
	public Label doResize() {
		label.doResize();
		return this;
	}
}
