package net.sschwarzbaer.java.tools.rick_and_morty_viewer_backend.ram_api;

import java.util.List;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RAMListResponse<ItemType> {
    public final RAMPageInfo info;
    public final List<ItemType> results;
    public RAMListResponse() { this(null, null); }
}