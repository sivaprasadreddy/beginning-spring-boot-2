import { combineReducers } from 'redux';
import { LOAD_POSTS, LOAD_POST } from '../constants';

function posts(state = [], action) {

    switch(action.type)
    {
        case LOAD_POSTS:
            return [...action.payload.posts];

        case LOAD_POST:
            return [action.payload.post];

        default:
            return state;
    }

}

const rootReducer = combineReducers({
    posts
});

export default rootReducer;