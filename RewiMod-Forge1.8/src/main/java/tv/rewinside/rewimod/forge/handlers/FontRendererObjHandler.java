/*
 * RewiMod, a Minecraft Client Enhancement
 * Copyright (C) rewinside.tv <https://rewinside.tv/>
 * Copyright (C) RewiMod team and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package tv.rewinside.rewimod.forge.handlers;

import net.minecraft.client.Minecraft;
import tv.rewinside.rewimod.core.handlers.IFontRendererObjHandler;

public class FontRendererObjHandler implements IFontRendererObjHandler {

	@Override
	public void drawString(String text, int x, int y, int color) {
		Minecraft.getMinecraft().fontRendererObj.drawString(text, x, y, color);
	}

	@Override
	public void drawStringWithShadow(String text, int x, int y, int color) {
		Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(text, x, y, color);
	}

	@Override
	public int getStringWidth(String text) {
		return Minecraft.getMinecraft().fontRendererObj.getStringWidth(text);
	}

}
