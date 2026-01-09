package com.grievance.service;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class AIService {

    public Map<String, String> analyzeGrievance(String text) {

        Map<String, String> result = new HashMap<>();
        text = text.toLowerCase();

        if (text.contains("road") || text.contains("pothole") || text.contains("highway")) {
            result.put("department", "Roadways Department");
        } else if (text.contains("water") || text.contains("pipeline") || text.contains("drainage")) {
            result.put("department", "Water Supply Department");
        } else if (text.contains("electric") || text.contains("power") || text.contains("current")) {
            result.put("department", "Electricity Department");
        } else if (text.contains("bus") || text.contains("transport") || text.contains("traffic")) {
            result.put("department", "Transport Department");
        } else if (text.contains("hospital") || text.contains("doctor")) {
            result.put("department", "Health Department");
        } else if (text.contains("crime") || text.contains("police") || text.contains("theft")) {
            result.put("department", "Police Department");
        } else {
            result.put("department", "Municipal Corporation");
        }

        if (text.contains("urgent")
                || text.contains("emergency")
                || text.contains("urgenc")) {   // 🔥 catches urgency, urgencies, urgency.
            result.put("urgency", "HIGH");
        } else {
            result.put("urgency", "NORMAL");
        }

        return result;
    }
}
