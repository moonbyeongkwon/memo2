package com.memo2.post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.memo2.comment.bo.CommentBO;
import com.memo2.comment.domain.CommentView;
import com.memo2.post.bo.PostBO;
import com.memo2.post.domain.Post;
import com.memo2.post.domain.PostView;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/post")
public class PostController {

	@Autowired
	private CommentBO commentBO;
	
	@Autowired
	private PostBO postBO;
	
	@GetMapping("/post-list-view")
	public String postView(
			@RequestParam(value = "prevId", required = false) Integer prevIdParam,
			@RequestParam(value = "nextId", required = false) Integer nextIdParam,
			Model model, HttpSession session) {
		//	로그인 여부
		Integer userId = (Integer)session.getAttribute("userId");
		if (userId == null) {
			return "redirect:/user/sign-in-view";
		}
		List<PostView> postViewList = postBO.generatePostViewList(userId, prevIdParam, nextIdParam);
		
		int prevId = 0;
		int nextId = 0;
		if (postViewList.isEmpty() == false) {
			prevId = postViewList.get(0).getPost().getId();
			nextId = postViewList.get(postViewList.size() -1).getPost().getId();
			
			if (postBO.isPrevLastPage(prevId)) {
				prevId = 0;
			}
			
			if (postBO.isNextLastPage(nextId)) {
				nextId = 0;
			}
		}
		
		
		
		
		model.addAttribute("prevId", prevId);
		model.addAttribute("nextId", nextId);
		model.addAttribute("postViewList", postViewList);
		model.addAttribute("viewName", "post/postList");
		
		return "template/layout";
	}
	
	/**
	 * 글쓰기
	 * @param model
	 * @return
	 */
	@GetMapping("/post-create-view")
	public String postCreateView(Model model) {
		model.addAttribute("viewName", "post/postCreate");
		return "template/layout";
	}
	
	/**
	 * 글 상세
	 * @param postId
	 * @param model
	 * @return
	 */
	@GetMapping("/post-detail-view")
	public String postDetailView(
			@RequestParam("postId") int postId,
			Model model) {
		
		//	select DB - userId, postId
		Post post = postBO.getPostByPostId(postId);
		
		List<CommentView> commentViewList = commentBO.generateCommentViewList(postId);
		
		PostView postDetailView = postBO.getPostDetailByPostId(postId);
		
		model.addAttribute("commentViewList", commentViewList);
		model.addAttribute("post", post);
		model.addAttribute("postView", postDetailView);
		model.addAttribute("viewName", "post/postDetail");
		return "template/layout";
	}
	
}
