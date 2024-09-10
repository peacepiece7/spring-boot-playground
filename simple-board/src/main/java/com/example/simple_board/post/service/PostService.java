package com.example.simple_board.post.service;

import com.example.simple_board.board.db.BoardEntity;
import com.example.simple_board.board.db.BoardRepository;
import com.example.simple_board.board.service.BoardService;
import com.example.simple_board.common.Api;
import com.example.simple_board.common.Pagination;
import com.example.simple_board.post.db.PostEntity;
import com.example.simple_board.post.db.PostRepository;
import com.example.simple_board.post.model.PostRequest;
import com.example.simple_board.post.model.PostViewRequest;
import com.example.simple_board.reply.db.ReplyRepository;
import com.example.simple_board.reply.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final ReplyService replyService;
    private final BoardRepository boardRepository;

    public PostEntity create(
            PostRequest postRequest
    ) {

        var boardEntity = boardRepository.findById(postRequest.getBoardId()).get(); // 임시 고정

        var entity = PostEntity.builder()
                .board(boardEntity)
                .userName(postRequest.getUserName())
                .password(postRequest.getPassword())
                .email(postRequest.getEmail())
                .status("REGISTERED")
                .title(postRequest.getTitle())
                .content(postRequest.getContent())
                .postedAt(LocalDateTime.now())
                .build();
        return postRepository.save(entity);
    }


    public PostEntity view(PostViewRequest postViewRequest) {
        var entity = postRepository.findFirstByIdAndStatusOrderByIdDesc(postViewRequest.getPostId(), "REGISTERED");

        if(entity.isEmpty()) {
            throw new RuntimeException("해당 게시글이 존재하지 않습니다." + postViewRequest.getPostId());
        }

        var postEntity = entity.get();
        if(!postEntity.getPassword().equals(postViewRequest.getPassword())) {
            var format = "패스워드가 맞지 않습니다 %s vs %s";
            throw new RuntimeException(String.format(format, postEntity.getPassword(), postViewRequest.getPassword()));
        }

        // 답글 가져오기
        //  var replyList = replyService.findAllByPostId(postEntity.getId());
        //  postEntity.setReplyList(replyList);
        
        return postEntity;
    }


    public Api<List<PostEntity>> all(Pageable1 pageable) {
        var list = postRepository.findAll(pageable);

        var pagination = Pagination.builder()
                .page(list.getNumber())
                .size(list.getSize())
                .currentElements(list.getNumberOfElements())
                .totalElements(list.getTotalElements())
                .totalPage(list.getTotalPages())
                .build();

        return Api.<List<PostEntity>>builder()
                .body(list.toList())
                .pagination(pagination)
                .build();
    }


    public void delete(PostViewRequest postViewRequest) {
        var entity = postRepository.findFirstByIdAndStatusOrderByIdDesc(postViewRequest.getPostId(), "REGISTERED");
        if(entity.isEmpty()) {
            throw new RuntimeException("해당 게시글이 존재하지 않습니다." + postViewRequest.getPostId());
        }

        var postEntity = entity.get();
        if (!postEntity.getPassword().equals(postViewRequest.getPassword())) {
            var format = "패스워드가 맞지 않습니다 %s vs %s";
            throw new RuntimeException(String.format(format, postEntity.getPassword(), postViewRequest.getPassword()));
        }

        postEntity.setStatus("UNREGISTERED");
        postRepository.save(postEntity);
    }
}
