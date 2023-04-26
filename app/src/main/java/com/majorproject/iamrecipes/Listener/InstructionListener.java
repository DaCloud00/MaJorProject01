package com.majorproject.iamrecipes.Listener;

import com.majorproject.iamrecipes.Models.InstructionsResponse;

import java.util.List;

public interface InstructionListener {
    void didFetch(List<InstructionsResponse> response, String message);
    void didError(String message);
}
