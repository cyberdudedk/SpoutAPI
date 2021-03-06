/*
 * This file is part of SpoutAPI (http://wiki.getspout.org/).
 * 
 * SpoutAPI is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SpoutAPI is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.getspout.spoutapi.gui;

public class GenericRectange extends GenericGradient{
	
	public GenericRectange(){
		
	}
	
	public GenericRectange(Color color) {
		setColor(color);
	}
	
	public GenericRectange setColor(Color color){
		super.setTopColor(color);
		super.setBottomColor(color);
		return this;
	}
	
	@Override
	public GenericGradient setTopColor(Color color) {
		return setColor(color);
	}
	
	@Override
	public GenericGradient setBottomColor(Color color) {
		return setColor(color);
	}

}
