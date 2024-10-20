import {createBrowserRouter} from "react-router-dom";
import guestRoutes from "./guest.routes.tsx";


export const browserRoutes = createBrowserRouter(
    [
        ...guestRoutes
    ]
)