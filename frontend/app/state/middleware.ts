import * as toolkitRaw from '@reduxjs/toolkit';
import toast from 'react-hot-toast';
export const rtkQueryErrorLogger: toolkitRaw.Middleware =
    (api: toolkitRaw.MiddlewareAPI) => (next) => (action) => {
        if (toolkitRaw.isRejectedWithValue(action)) {
            toast.error(action.error.message, {
                position: "top-center",
            });
        }
        return next(action)
    }