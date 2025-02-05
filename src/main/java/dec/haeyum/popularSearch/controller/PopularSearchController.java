package dec.haeyum.popularSearch.controller;

import dec.haeyum.chat.dto.ChatRoomDto;
import dec.haeyum.chat.repository.ChatRoomRepository;
import dec.haeyum.popularSearch.dto.PopularChatroomDto;
import dec.haeyum.popularSearch.dto.PopularSearchDto;
import dec.haeyum.popularSearch.service.PopularSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class PopularSearchController {

    private final PopularSearchService popularSearchService;


    @GetMapping("/ranking/search")
    public ResponseEntity<Map<String, Object>> getPopularSearch() {
        List<PopularSearchDto> popularSearch = popularSearchService.getPopularSearch();

        Map<String, Object> response = new HashMap<>();
        response.put("searches", popularSearch);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/ranking/chatroom")
    public ResponseEntity<List<PopularChatroomDto>> getPopularSearchChatroom() {
        List<PopularChatroomDto> popularChatRoom = popularSearchService.getPopularChatRoom();
        return ResponseEntity.ok(popularChatRoom);
    }
}
