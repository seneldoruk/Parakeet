import * as toolkitRaw from '@reduxjs/toolkit';
import postsSlice from './postsSlice';
import userSlice from './userSlice';
const { configureStore } = ((toolkitRaw as any).default ?? toolkitRaw) as typeof toolkitRaw;



export const store = configureStore({
    reducer: {
        posts: postsSlice,
        user: userSlice
    },
    devTools: true,
    middleware: (getDefaultMiddleware) => getDefaultMiddleware({}).concat([]),
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;