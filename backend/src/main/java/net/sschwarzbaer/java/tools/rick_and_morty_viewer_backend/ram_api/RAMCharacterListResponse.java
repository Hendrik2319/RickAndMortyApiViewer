package net.sschwarzbaer.java.tools.rick_and_morty_viewer_backend.ram_api;

import java.util.List;

public record RAMCharacterListResponse(RAMPageInfo info, List<RAMCharacter> results) {
}