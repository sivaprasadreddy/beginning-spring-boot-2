import {LOAD_POSTS, LOAD_POST} from "../constants";

export function loadPosts(posts) {
    return {
        type: LOAD_POSTS,
        payload: {
            posts: posts
        }
    }
}

export function loadPost(post) {
    return {
        type: LOAD_POST,
        payload: {
            post: post
        }
    }
}
