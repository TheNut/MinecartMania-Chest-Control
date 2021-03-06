package com.afforess.minecartmaniachestcontrol.signs;

import com.afforess.minecartmaniacore.world.AbstractItem;
import com.afforess.minecartmaniacore.minecart.MinecartManiaMinecart;
import com.afforess.minecartmaniacore.minecart.MinecartManiaStorageCart;
import com.afforess.minecartmaniacore.signs.Sign;
import com.afforess.minecartmaniacore.signs.SignAction;
import com.afforess.minecartmaniacore.utils.ItemUtils;

public class MaximumItemAction implements SignAction{
	protected AbstractItem items[] = null;
	public MaximumItemAction(Sign sign) {
		this.items = ItemUtils.getItemStringListToMaterial(sign.getLines());
	}

	@Override
	public boolean execute(MinecartManiaMinecart minecart) {
		if (minecart.isStorageMinecart()) {
			for (AbstractItem item : items) {
				((MinecartManiaStorageCart)minecart).setMaximumItem(item.type(), item.getAmount());
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean async() {
		return true;
	}

	@Override
	public boolean valid(Sign sign) {
		if (sign.getLine(0).toLowerCase().contains("max item")) {
			sign.addBrackets();
			return true;
		}
		return false;
	}

	@Override
	public String getName() {
		return "maximumitemsign";
	}

	@Override
	public String getFriendlyName() {
		return "Maximum Item Sign";
	}

}
