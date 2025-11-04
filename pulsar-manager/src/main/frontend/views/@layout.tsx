import {Outlet} from "react-router";
import {AuthProvider} from "Frontend/auth";

export default function MainLayout() {

    return (
        <AuthProvider>
            <Outlet />
        </AuthProvider>
    );
}