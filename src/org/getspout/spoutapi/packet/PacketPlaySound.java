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
package org.getspout.spoutapi.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.bukkit.Location;
import org.getspout.spoutapi.sound.Music;
import org.getspout.spoutapi.sound.SoundEffect;

public class PacketPlaySound implements SpoutPacket{
	short soundId;
	boolean location = false;
	int x, y, z;
	int volume, distance;
	
	public PacketPlaySound() {
		
	}
	
	public PacketPlaySound(SoundEffect sound, int distance, int volume) {
		soundId = (short) sound.getId();
		this.volume = volume;
		this.distance = distance;
	}
	
	public PacketPlaySound(SoundEffect sound, Location loc, int distance, int volume) {
		soundId = (short) sound.getId();
		location = true;
		x = loc.getBlockX();
		y = loc.getBlockY();
		z = loc.getBlockZ();
		this.volume = volume;
		this.distance = distance;
	}
	
	public PacketPlaySound(Music music, int volume) {
		soundId = (short) (music.getId() + (1 + SoundEffect.getMaxId()));
		this.volume = volume;
	}

	@Override
	public int getNumBytes() {
		return 23;
	}

	@Override
	public void readData(DataInputStream input) throws IOException {
		soundId = input.readShort();
		location = input.readBoolean();
		x = input.readInt();
		y = input.readInt();
		z = input.readInt();
		distance = input.readInt();
		volume = input.readInt();
	}

	@Override
	public void writeData(DataOutputStream output) throws IOException {
		output.writeShort(soundId);
		output.writeBoolean(location);
		if (!location) {
			output.writeInt(-1);
			output.writeInt(-1);
			output.writeInt(-1);
			output.writeInt(-1);
		}
		else {
			output.writeInt(x);
			output.writeInt(y);
			output.writeInt(z);
			output.writeInt(distance);
		}
		output.writeInt(volume);
	}

	@Override
	public void run(int PlayerId) {
		
	}

	@Override
	public void failure(int id) {
		
	}

	@Override
	public PacketType getPacketType() {
		return PacketType.PacketPlaySound;
	}
	
	@Override
	public int getVersion() {
		return 0;
	}

}
