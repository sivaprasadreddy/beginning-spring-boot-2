import React from 'react';
import ReactDOM from 'react-dom';
import { Router, Route, browserHistory } from 'react-router';
import { Provider } from 'react-redux';

import configureStore from './store';

import Home from './components/Home';
import NewPost from './components/NewPost';
import ViewPost from './components/ViewPost';

let store = configureStore();

ReactDOM.render(
    <Provider store={store}>
        <Router history={browserHistory}>
            <Route path="/" component={Home}/>
            <Route path="/newpost" component={NewPost}/>
            <Route path="/posts/:id" component={ViewPost}/>

        </Router>
    </Provider>
    ,
    document.getElementById('root')
);