package com.timon.war;
import com.google.gson.JsonObject;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Base64;


@Mod.EventBusSubscriber

public class EventHandler {
    @SubscribeEvent
   public void onChatReceived(ClientChatReceivedEvent event) {
        String message = event.getMessage().getUnformattedText();
        if (message.startsWith("- Captured")) {
            String territory = message.substring(12, message.length() - 1);
            String UUID = String.valueOf(Minecraft.getMinecraft().player.getUniqueID());
            JsonObject object = new JsonObject();
            object.addProperty("timestamp", System.currentTimeMillis());
            object.addProperty("Name", territory);
            object.addProperty("uuid", UUID);
            File file = new File(Minecraft.getMinecraft().mcDataDir,"war.json");
            String key = Base64.getEncoder().encodeToString(object.toString().getBytes());
            String url = "http://64.226.79.170:8000/war?uuid="+UUID+"&key="+key;
            URL obj = null;
            try {obj = new URL(url);} catch (MalformedURLException e) {throw new RuntimeException(e);}
            HttpURLConnection con = null;
            try {con = (HttpURLConnection) obj.openConnection();} catch (IOException e) {throw new RuntimeException(e);}
            try {con.setRequestMethod("GET");} catch (ProtocolException e) {throw new RuntimeException(e);}
            int responseCode = 0;
            try {responseCode = con.getResponseCode();} catch (IOException e) {throw new RuntimeException(e);}
        }
   }
}
