package org.launchcode.controllers;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.launchcode.models.JobData.allJobs;
import static org.launchcode.models.JobData.loadData;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    /*
    TODO #1 - Create handler to process search request and display results
    Search with two params; searchType and searchTerm from input on search.html
    */
    @RequestMapping("/results")
    public String searchByTerm(Model model,
                                           @RequestParam String searchType, @RequestParam String searchTerm) {

        ArrayList<HashMap<String, String>> jobs;
        if (searchType.toLowerCase().equals("all")  || searchTerm.trim().equals("")) {
            jobs = JobData.findByValue(searchTerm);

        }
        else{
            jobs = JobData.findByColumnAndValue(searchType, searchTerm);
        }
        model.addAttribute("columns", ListController.columnChoices);
        model.addAttribute("jobs", jobs);

        return "search";
    }


}

