package com.zzz.controller.websocket;

import com.zzz.result.ResponseCode;
import com.zzz.result.Results;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;


@RestController
public class WebSocketController {

    @GetMapping("websocketDemo")
    public ModelAndView getDemoPage(){
        return new ModelAndView("websocket");
    }

    @PostMapping("/websocket/push")
    public Results<String> pushToWeb(@RequestParam String message, @RequestParam String toUserId) throws IOException {
        WebSocketServer.sendInfo(message,toUserId);
        return Results.success(ResponseCode.SUCCESS, "发送成功");
    }
}


