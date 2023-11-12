import * as toolkitRaw from '@reduxjs/toolkit/dist/query/react/index.js';
const { createApi, fetchBaseQuery } = ((toolkitRaw as any).default ?? toolkitRaw) as typeof toolkitRaw;
import { baseUrl } from './baseUrl';

interface Request {
    username: string,
    password: string
}
interface LoginRes {
    token: string,
    following: string[]
}
interface RegisterRes {
    token: string,
}

// Define a service using a base URL and expected endpoints
export const authApi = createApi({
    reducerPath: 'authApi',
    baseQuery: baseUrl,
    endpoints: (builder) => ({
        login: builder.query<LoginRes, Request>({
            query: (body) => ({
                url: 'auth/login',
                method: 'POST',
                body: body
            }),
        }),
        register: builder.query<RegisterRes, Request>({
            query: (body) => ({
                url: 'auth/register',
                method: 'POST',
                body: body
            }),
        }),
    }),
})

// Export hooks for usage in functional components, which are
// auto-generated based on the defined endpoints
export const { useLazyLoginQuery, useLazyRegisterQuery } = authApi

