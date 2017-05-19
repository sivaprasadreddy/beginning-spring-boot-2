import axios from 'axios';
import { API_ROOT_PATH } from '../constants';
import * as actionCreators from "../actions/actionCreators";

export function fetchPosts() {
    return dispatch => {

        axios.get(API_ROOT_PATH+'/posts')
            .then(response =>
                dispatch(actionCreators.loadPosts(response.data._embedded.posts))
            )
            .catch((err)=>{
                console.error('Fetch posts ERROR:',err)
            })
        ;
    }
}

export function fetchPost(id) {
    return dispatch => {

        axios.get(API_ROOT_PATH+'/posts/'+id)
            .then(response =>
                dispatch(actionCreators.loadPost(response.data))
            )
            .catch((err)=>{
                console.error('Fetch post ERROR:',err)
            })
        ;
    }
}

export function createPost(post) {
    return dispatch => {

        var config = {
            headers: {'Content-Type': 'application/json'}
        };
        axios({
            url: API_ROOT_PATH+'/posts',
            method: 'post',
            data: post
        }, config)
            .then(function(response) {
                console.log('Saved post :',response);
                dispatch(fetchPosts());
            })
            .catch(function(response){
                console.error('Save post ERROR:',response);
            })
    }
}

export function deletePost(id) {
    return dispatch => {

        var config = {
            headers: {'Content-Type': 'application/json'}
        };
        axios({
            url: API_ROOT_PATH+'/posts/'+id,
            method: 'DELETE'
        }, config)
            .then(function(response) {
                console.log('Deleted post :',response);
                dispatch(fetchPosts());
            })
            .catch(function(response){
                console.error('Delete post ERROR:',response);
            })
    }
}