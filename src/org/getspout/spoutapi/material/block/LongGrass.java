package org.getspout.spoutapi.material.block;

import org.getspout.spoutapi.material.Plant;

public class LongGrass extends GenericBlock implements Plant{

	public LongGrass(String name, int id, int data) {
		super(name, id, data);
	}

	@Override
	public boolean isHasGrowthStages() {
		return false;
	}

	@Override
	public int getNumGrowthStages() {
		return 0;
	}

	@Override
	public int getMinimumLightToGrow() {
		return 0;
	}

}
