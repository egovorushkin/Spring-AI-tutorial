package com.egovorushkin.spring_ai;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

@Service
public class WeatherTools {

    @Tool(name = "get-weather-for-zip-code", description = "Get weather for a given zip code")
    public Weather getWeatherForZipCode(
            @ToolParam (description = "The zip code to get the weather for") String zipCode) {
        return new Weather(zipCode, "Raining cats and dogs", "20°C");
    }
}
