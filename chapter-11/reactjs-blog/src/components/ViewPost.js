import React from 'react';
import {connect} from "react-redux";
import {browserHistory} from 'react-router';
import * as actions from "../actions/index";
import Post from './Post';

class ViewPost extends React.Component {
    constructor(props) {
        super(props);
        this.handleDeletePost = this.handleDeletePost.bind(this);
    }

    componentWillMount() {
        const {dispatch} = this.props;
        dispatch(actions.fetchPost(this.props.params['id']));
    }

    handleDeletePost(id) {
        const {dispatch} = this.props;
        dispatch(actions.deletePost(id));
        browserHistory.push('/');
    }

    render() {
        return (
            <div>
                <Post post={this.props.post}/>
                <button className="btn btn-danger" onClick={() => this.handleDeletePost(this.props.post.id)}>Delete
                </button>
            </div>
        );
    }
}

const mapStateToProps = (state) => {
    return {
        post: state.posts[0]
    }
};

export default connect(mapStateToProps)(ViewPost);
