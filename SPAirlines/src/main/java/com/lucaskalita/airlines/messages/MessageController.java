package com.lucaskalita.airlines.messages;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Message")
public class MessageController {

    private final MessageService messageService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public MessageDTO findById(@PathVariable Long id){
        return messageService.findMessageByID(id);
    }
    @PostMapping("/sendMessage/{sender}/{receiver}")
    @ResponseStatus(HttpStatus.CREATED)
    public MessageDTO createMessage(@RequestBody NoteDTO noteDTO, String sender, String receiver){
        return messageService.sendMessage(noteDTO, sender, receiver);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteMessage(@PathVariable Long id){
        messageService.deleteMessageById(id);
    }
    @GetMapping("/messagesSentTo/{sender}_{receiver}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<MessageDTO>  findMessagesBetweenUsers(@PathVariable String sender, @PathVariable String receiver){
        return messageService.findConversationBetween(sender, receiver);
    }
    @GetMapping("/toUser/{username}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<MessageDTO> messagesSentToUser (@PathVariable String username){
        return messageService.findAllSentMessagesToUser(username);
    }
    @GetMapping("/ByUser/{username}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<MessageDTO> messagesSentByUser (@PathVariable String username){
        return messageService.findAllMessagesSentByUser(username);
    }

    @GetMapping("/messagesSentBetween/{date1}_{date2}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<MessageDTO>findAllMessagesSentBetweenTime(@PathVariable LocalDateTime date1, @PathVariable LocalDateTime date2){
        return messageService.findAllMessagesSentBetween(date1, date2);
    }
    @GetMapping("/messagesSentAfter/{date1}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<MessageDTO>findAllMessagesSentAfter(@PathVariable LocalDateTime date1){
        return messageService.findAllMessagesSentAfter(date1);
    }
    @GetMapping("/messagesSentBefore/{date1}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<MessageDTO>findAllMessagesSentBefore(@PathVariable LocalDateTime date1){
        return messageService.findAllMessagesSentBefore(date1);
    }
}
