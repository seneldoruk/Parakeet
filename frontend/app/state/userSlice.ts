import { PayloadAction } from "@reduxjs/toolkit";
import * as toolkitRaw from '@reduxjs/toolkit';
const { createSlice } = ((toolkitRaw as any).default ?? toolkitRaw) as typeof toolkitRaw;
interface UserSlice {
    username?: string,
    following: string[]
}
const initialState: UserSlice = {
    username: undefined,
    following: []
}
const userSlice = createSlice({
    name: "userSlice",
    initialState,
    reducers: {
        setUser: function (state, action: PayloadAction<UserSlice>) {
            state = action.payload
        }
    }
})
export const { ...all } = userSlice.actions;
export default userSlice.reducer;