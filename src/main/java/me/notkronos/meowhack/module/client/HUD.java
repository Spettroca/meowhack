package me.notkronos.meowhack.module.client;

import me.notkronos.meowhack.Meowhack;
import me.notkronos.meowhack.module.Category;
import me.notkronos.meowhack.module.Module;
import me.notkronos.meowhack.setting.Setting;
import me.notkronos.meowhack.util.render.FontUtil;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.text.TextFormatting;

import java.text.DecimalFormat;

import static me.notkronos.meowhack.util.Wrapper.mc;

public class HUD extends Module {
    public static HUD INSTANCE;

    public HUD() {
        super("HUD", Category.CLIENT, "HUD of the client", new String[]{});
        INSTANCE = this;
        INSTANCE.drawn = false;
        INSTANCE.enabled = true;
    }

    private static final DecimalFormat decimalFormat = new DecimalFormat("0.0");

    //Settings
    public static Setting<Boolean> watermark = new Setting<>("Watermark", true);
    public static Setting<Boolean> direction = new Setting<>("Direction", true);
    public static Setting<Boolean> coordinates = new Setting<>("Coordinates", true);

    private static final float ELEMENT = FontUtil.getFontHeight() + 1;

    @Override
    public void onRender2D() {
        ScaledResolution resolution = new ScaledResolution(mc);
        int width = resolution.getScaledWidth();
        int height = resolution.getScaledHeight();
        float topLeft = 2;
        float topRight = 2;
        float bottomLeft = height - ELEMENT;
        float bottomRight = height - ELEMENT;

        StringBuilder watermarkS = null;
        if (watermark.getValue()) {
            watermarkS = new StringBuilder()
                    .append(Meowhack.NAME)
                    .append(" v.")
                    .append(Meowhack.VERSION);

            FontUtil.drawStringWithShadow(watermarkS.toString(), 2, topLeft, 0xff80ff);
            topLeft += ELEMENT;
        }

        if(coordinates.getValue()) {
            double X = Double.parseDouble(decimalFormat.format(mc.player.posX));
            double Y = Double.parseDouble(decimalFormat.format(mc.player.posY));
            double Z = Double.parseDouble(decimalFormat.format(mc.player.posZ));

            String coordinateS = TextFormatting.GRAY +
                    "XY Z" +
                    TextFormatting.WHITE +
                    X +
                    TextFormatting.GRAY +
                    ", " +
                    TextFormatting.WHITE +
                    Y +
                    TextFormatting.GRAY +
                    ", " +
                    TextFormatting.WHITE +
                    Z;

            FontUtil.drawStringWithShadow(coordinateS, 2, bottomLeft, 0xffffff);
            bottomLeft -= ELEMENT;
        }

    }
}