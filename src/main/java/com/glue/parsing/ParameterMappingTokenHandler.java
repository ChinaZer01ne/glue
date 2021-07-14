package com.glue.parsing;

import com.glue.Configuration;
import com.glue.mapping.ParameterMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ParameterMappingTokenHandler implements TokenHandler {

    private List<ParameterMapping> parameterMappings = new ArrayList<>();

    public List<ParameterMapping> getParameterMappings() {
      return parameterMappings;
    }

    @Override
    public String handleToken(String content) {
      parameterMappings.add(buildParameterMapping(content));
      return "?";
    }

    private ParameterMapping buildParameterMapping(String content) {
      return new ParameterMapping(content);
    }

}
