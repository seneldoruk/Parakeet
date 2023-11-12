import * as toolkitRaw from '@reduxjs/toolkit/dist/query/react/index.js';
const { fetchBaseQuery } = ((toolkitRaw as any).default ?? toolkitRaw) as typeof toolkitRaw;


export const baseUrl = fetchBaseQuery({
    baseUrl: 'http://localhost:8080/', prepareHeaders: (headers) => {
        headers.set("Authorization", `Bearer ${localStorage.getItem("token")}`)
    }
})