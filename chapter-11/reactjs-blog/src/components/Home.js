import React from 'react';
import {connect} from "react-redux";

import * as actions from "../actions/index";
import Post from './Post';

class Home extends React.Component {

    componentWillMount() {
        const {dispatch} = this.props;
        dispatch(actions.fetchPosts());
    }

    render() {
        const posts = this.props.posts.map((post) => {
            return <Post key={post.id} post={post}/>
        });

        return (
            <div>{posts}</div>
        );
    }
}

const mapStateToProps = (state) => {
    return {
        posts: state.posts
    }
}

export default connect(mapStateToProps)(Home);