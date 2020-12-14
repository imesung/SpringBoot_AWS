package com.mesung.book.springboot.service.posts;

import com.mesung.book.springboot.domain.posts.Posts;
import com.mesung.book.springboot.domain.posts.PostsRepository;
import com.mesung.book.springboot.web.dto.PostsListResponseDto;
import com.mesung.book.springboot.web.dto.PostsResponseDto;
import com.mesung.book.springboot.web.dto.PostsSaveRequestDto;
import com.mesung.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {

    //Autowired가 없네? 이유는 생성자로 의존성 주입을 받기 때문인다(@RequiredArgsContructor)
    //롬복을 사용하게 되면 해당 클래스의 의존성 관계가 변경되어도 코드 변경이 필요없기 때문이다.
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }
    //신기한 것은 update 기능에서 데이터베이스에 쿼리를 날리는 부분이 없다. 이것이 가능한 이유는 JPA의 영속성 컨텍스트 때문이다.
    //영속성 컨텍스는 엔티티를 영구 저장하는 환경이다.
    //이 상태에서 해당 데이터의 값을 변경하면 트랜잭션이 끝나는 시점에 해당 테이블에 변경분을 반영한다. 즉, Enttity 객체의 값만 변경하면
    //별도로 Update 쿼리를 날리 필요가 없다.(더티 체킹)

    @Transactional
    public void delete (Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        postsRepository.delete(posts);
    }

    @Transactional(readOnly = true)
    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }
}
