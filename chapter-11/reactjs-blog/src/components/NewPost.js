import React from 'react';
import {browserHistory} from 'react-router';
import {connect} from "react-redux";

import * as actions from '../actions/index';

class NewPost extends React.Component {
    constructor(props) {
        super(props);
        this.handleAddNewPost = this.handleAddNewPost.bind(this);
    }

    handleAddNewPost(e) {
        e.preventDefault();
        const {dispatch} = this.props;
        let post = {
            title: this.title.value,
            content: this.content.value
        };
        dispatch(actions.createPost(post));
        browserHistory.push('/');
    }

    render() {
        return (
            <div>
                <h2>NewPost Page</h2>

                <form onSubmit={this.handleAddNewPost}>
                    <div className="form-group">
                        <label htmlFor="title">Title</label>
                        <input type="text" className="form-control" id="title" ref={(input) => this.title = input }/>
                    </div>
                    <div className="form-group">
                        <label htmlFor="content">Content</label>
                        <textarea name="content" className="form-control" rows="10" ref={(input) => this.content = input }/>
                    </div>
                    <div className="form-group">
                        <button type="submit" className="btn btn-primary">Submit</button>
                    </div>

                </form>

            </div>
        );
    }
}

export default connect()(NewPost);