package com.travel.letsgospringboot.myschedule.controller;

import com.travel.letsgospringboot.myschedule.service.MyScheduleService;
import jakarta.websocket.Session;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/myschedule")
@RequiredArgsConstructor
public class MyScheduleController {

    private final MyScheduleService myScheduleService;

    @GetMapping("/list")
    @ResponseBody
    public Collection getMyScheduleList(@RequestParam(name = "userId", required = true) String userId, SearchRequest searchRequest) {
        boolean isSortTitle = searchRequest.getSortType().equals("title");
        if (searchRequest.isShared()) {
            if (searchRequest.getSearchTitle().isEmpty()) {
                if (isSortTitle)
                    return myScheduleService.getMyScheduleListSearchSharedByTitle(userId, searchRequest.getSearchTitle());
                return myScheduleService.getMyScheduleListSearchSharedByDate(userId, searchRequest.getSearchTitle());
            } else {
                if (isSortTitle)
                    return myScheduleService.getMyScheduleListSharedByTitle(userId);
                return myScheduleService.getMyScheduleListSharedByDate(userId);
            }
        } else {
            if (searchRequest.getSearchTitle().isEmpty()) {
                if (isSortTitle)
                    return myScheduleService.getMyScheduleListSearchByTitle(userId, searchRequest.getSearchTitle());
                return myScheduleService.getMyScheduleListSearchByDate(userId, searchRequest.getSearchTitle());
            } else {
                if (isSortTitle)
                    return myScheduleService.getMyScheduleListAllByTitle(userId);
                return myScheduleService.getMyScheduleListAllByDate(userId);
            }
        }
    }
}
