package com.plexonic.analyticsapi.controller;

import com.plexonic.analyticsapi.exception.service.InvalidEntryException;
import com.plexonic.analyticsapi.exception.service.ServiceException;
import com.plexonic.analyticsapi.model.User;
import com.plexonic.analyticsapi.service.RequestService;
import com.plexonic.analyticsapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/rest")
public class APIController {

    private RequestService requestService;
    private UserService userService;

    @Autowired
    public APIController(RequestService requestService, UserService userService) {
        this.requestService = requestService;
        this.userService = userService;
    }


    /**
     * API for getting Daily active users
     *
     * @param jsonMapOfDates
     * @return
     */
    @RequestMapping(value = "/daus", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, List<String>>> getDAU(@RequestBody Map<String, List<String>> jsonMapOfDates) {

        List<String> userList;
        Map<String, List<String>> userMap = new HashMap<>();

        // Get and send Daily active user list
        for (List<String> valueList : jsonMapOfDates.values()) {
            for (String date : valueList) {
                try {
                    userList = requestService.getDailyActiveUsers(date);
                    userMap.put(date, userList);
                } catch (InvalidEntryException e) {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                } catch (ServiceException e) {
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        }
        return new ResponseEntity<>(userMap, HttpStatus.OK);
    }


    /**
     * API for getting Retention
     *
     * @param jsonMap
     * @return
     */
    @RequestMapping(value = "/retentions", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> getRetentionForGivenPeriod(@RequestBody Map<String, String> jsonMap) {

        String retentionType = jsonMap.get("type");
        String dateStr = jsonMap.get("date");

        List<Map<String, Object>> userMap;

        // Get and send user retention for the given period
        try {
            userMap = requestService.getRetentionForGivenPeriod(retentionType, dateStr);
        } catch (InvalidEntryException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(userMap, HttpStatus.OK);
    }


    /**
     * API for getting user
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    public ResponseEntity<?> getRetentionForGivenPeriod(@PathVariable("userId") String userId) {

        User user;

        // Get and send user for given id
        try {
            user = userService.getUser(userId);
        } catch (InvalidEntryException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
