package shop.mtcoding.springblogriver.bookmark;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.springblogriver._core.error.exception.Exception403;
import shop.mtcoding.springblogriver._core.error.exception.Exception404;
import shop.mtcoding.springblogriver.post.Post;
import shop.mtcoding.springblogriver.post.PostRepository;
import shop.mtcoding.springblogriver.user.User;

@Slf4j
@RequiredArgsConstructor
@Service
public class BookmarkService {
    private final PostRepository postRepository;
    private final BookmarkRepository bookmarkRepository;

    @Transactional
    public BookmarkResponse.DTO 북마크(BookmarkRequest.SaveDTO requestDTO, User sessionUser){
        System.out.println("sessionUser id 1: "+sessionUser.getId());
        Post postPS = postRepository.findById(requestDTO.getPostId())
                .orElseThrow(() -> new Exception404("해당 id를 찾을 수 없습니다 : "+requestDTO.getPostId()));
        System.out.println("sessionUser id 2: "+sessionUser.getId());
        Bookmark bookmarkPS = bookmarkRepository.save(requestDTO.toEntity(sessionUser, postPS));
        return new BookmarkResponse.DTO(bookmarkPS);
    }

    @Transactional
    public void 북마크취소(Integer id, int sessionUserId) {
        Bookmark bookmarkPS = bookmarkRepository.findById(id)
                .orElseThrow(() -> new Exception404("해당 id를 찾을 수 없습니다 : "+id));
        if(bookmarkPS.getUser().getId() != sessionUserId) throw new Exception403("북마크를 삭제할 권한이 없습니다");

        bookmarkRepository.deleteById(id);
    }
}
