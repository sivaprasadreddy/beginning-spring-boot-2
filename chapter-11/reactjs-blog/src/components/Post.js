import React from 'react';
import {Link} from 'react-router';

export default class Post extends React.Component {
    render() {
        return (
            <div>
                <h2><Link to={"/posts/" + this.props.post.id}>{this.props.post.title}</Link></h2>
                <p>{this.props.post.content}</p>
            </div>
        );
    }
}