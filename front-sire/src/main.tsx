import {StrictMode} from 'react'
import {createRoot} from 'react-dom/client'
import {RouterProvider} from "react-router-dom";
import {browserRoutes} from "./routes/router.tsx";

createRoot(document.getElementById('root')!).render(
    <StrictMode>
        <RouterProvider router={browserRoutes}/>
    </StrictMode>,
)