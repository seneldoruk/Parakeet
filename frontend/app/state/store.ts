import * as toolkitRaw from '@reduxjs/toolkit';
import postsSlice from './postsSlice';
import userSlice from './userSlice';
import { authApi } from '~/api/authApi';
import { toast } from 'react-hot-toast';
import { rtkQueryErrorLogger } from './middleware';
const { configureStore } = ((toolkitRaw as any).default ?? toolkitRaw) as typeof toolkitRaw;




export const store = configureStore({
    reducer: {
        posts: postsSlice,
        user: userSlice,
        [authApi.reducerPath]: authApi.reducer,
    },
    devTools: true,
    middleware: (getDefaultMiddleware) => getDefaultMiddleware().concat([rtkQueryErrorLogger]),
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;
