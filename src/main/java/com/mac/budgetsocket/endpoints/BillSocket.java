/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.budgetsocket.endpoints;

import com.mac.budgetsocket.DaoConfig;
import com.mac.budgetsocket.endpoints.helpers.ModelProcessor;
import com.mac.budgetsocket.pojos.ui.BudgetUIModel;
import com.mac.budgetsocket.pojos.ui.Console;
import com.mac.jsonconverter.JsonConverter;
import com.mac.websocketmanager.WebSocketManager;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author Mac
 */
public class BillSocket extends WebSocketServer {

    private static final int PORT = 9095;

    private final WebSocketManager socketManager;
    private final ApplicationContext ctx;
    private final ModelProcessor mp;
    
    public BillSocket() {
        super(new InetSocketAddress(PORT));
        ctx = new AnnotationConfigApplicationContext(DaoConfig.class);
        socketManager = new WebSocketManager();
        mp = ctx.getBean(ModelProcessor.class);
    }

    @Override
    public void onOpen(WebSocket ws, ClientHandshake ch) {
        socketManager.addWebSocket(ws, ch);
    }

    @Override
    public void onClose(WebSocket ws, int i, String string, boolean bln) {
        if (Objects.nonNull(ws)) {
            socketManager.removeSocket(ws);
        }
    }

    @Override
    public void onMessage(WebSocket ws, String message) {
        Logger.getLogger(getClass().getName()).log(Level.INFO, message, message);
        if (Objects.nonNull(message) && !message.isEmpty()) {
            try {
                BudgetUIModel buim = (BudgetUIModel) JsonConverter.fromJsonString(message, BudgetUIModel.class);
                Console console = mp.processModel(buim);
                socketManager.sendMessageToAllSockets(JsonConverter.toJsonString(console));
            } catch (IOException ex) {
                Logger.getLogger(BillSocket.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void onError(WebSocket ws, Exception excptn) {
        if (Objects.nonNull(ws)) {
            if (ws.isOpen()) {
                ws.close();
            }
            socketManager.removeSocket(ws);
        }
    }
}
