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
package tv.rewinside.rewimod.forge.gui;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiLabel;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.fml.common.FMLCommonHandler;
import tv.rewinside.rewimod.core.RewiMod;
import tv.rewinside.rewimod.core.gui.CoreGuiDrawer;
import tv.rewinside.rewimod.core.gui.IGui;
import tv.rewinside.rewimod.core.gui.objects.IGuiButton;
import tv.rewinside.rewimod.core.util.CoordinateUtil;
import tv.rewinside.rewimod.core.util.RewiButtonConnectType;
import tv.rewinside.rewimod.forge.gui.objects.GuiRewiConnectButton;
import tv.rewinside.rewimod.forge.gui.objects.GuiRewiModButton;
import tv.rewinside.rewimod.forge.gui.objects.GuiTwitterButton;
import tv.rewinside.rewimod.forge.gui.objects.GuiYoutubeButton;

public class GuiRewiMainMenu extends GuiMainMenu implements IGui {

	private final Map<Integer, IGuiButton> buttons = new HashMap<>();
    
	@Override
	public void initGui() {
		this.buttons.clear();
		super.initGui();

		int lastId = super.buttonList.size();

		lastId += 100; //Mojang Nailed it

		super.buttonList.add(this.registerButton(new GuiRewiConnectButton(RewiButtonConnectType.MINECRAFT, lastId++, this.width / 2 + 80, this.height / 4 + 72)));
		super.buttonList.add(this.registerButton(new GuiRewiConnectButton(RewiButtonConnectType.TEAMSPEAK, lastId++, this.width / 2 - 100, this.height / 4 + 72)));
		super.buttonList.add(this.registerButton(new GuiRewiModButton(lastId++, this.width / 2 + 104, this.height / 4 + 48)));
		super.buttonList.add(this.registerButton(new GuiTwitterButton(lastId++, this.width / 2 + 104, this.height / 4 + 72)));
		super.buttonList.add(this.registerButton(new GuiYoutubeButton(lastId++, this.width / 2 + 104, this.height / 4 + 96)));
		super.buttonList.get(1).width = 150;
		super.buttonList.get(1).xPosition = this.width / 2 - 75;
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		ScaledResolution resolution = new ScaledResolution(this.mc);//TODO: Implement in core
		CoreGuiDrawer.drawBackgroundImage(this.width, this.height, resolution.getScaledWidth(), resolution.getScaledHeight());
		CoreGuiDrawer.drawMojangCopyright(this.width, this.height);
		CoreGuiDrawer.drawMojangLogo(this, this.width);

		//Render forge informations and MCP version?
		List<String> brandings = Lists.reverse(FMLCommonHandler.instance().getBrandings(true));
		for (int brdline = 0; brdline < brandings.size(); brdline++) {
			String brd = brandings.get(brdline);
			if (!Strings.isNullOrEmpty(brd)) {
				RewiMod.getInstance().getFontRendererObjHandler().drawStringWithShadow(brd, 2, this.height - ( 10 + brdline * (this.fontRendererObj.FONT_HEIGHT + 1)), 16777215);
			}
		}

		ForgeHooksClient.renderMainMenu(this, this.fontRendererObj, this.width, this.height);

		this.drawButtons(mouseX, mouseY);
	}
	
	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		super.mouseClicked(mouseX, mouseY, mouseButton);

		for (Map.Entry entry : this.buttons.entrySet()) {
			GuiButton guiButton = (GuiButton) entry.getValue();

			if (CoordinateUtil.inbetween(guiButton.xPosition, guiButton.yPosition, mouseX, mouseY, guiButton.width, guiButton.height)) {
				((IGuiButton) guiButton).onClick(mouseButton);
			}
		}
	}

	private GuiButton registerButton(IGuiButton button) {
		GuiButton btn = (GuiButton) button;
		this.buttons.put(btn.id, button);

		return btn;
	}

	private void drawButtons(int mouseX, int mouseY) {
        for (int i = 0; i < this.buttonList.size(); ++i) {
            ((GuiButton)this.buttonList.get(i)).drawButton(this.mc, mouseX, mouseY);
        }

        for (int i = 0; i < this.labelList.size(); ++i) {
            ((GuiLabel)this.labelList.get(i)).drawLabel(this.mc, mouseX, mouseY);
        }
    }
	
}
