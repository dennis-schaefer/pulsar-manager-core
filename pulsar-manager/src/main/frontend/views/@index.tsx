import {ViewConfig} from "@vaadin/hilla-file-router/types.js";
import {useAuth} from "Frontend/auth";

export const config: ViewConfig = {
    title: 'Pulsar Manager',
    loginRequired: true
}

export default function HomeView() {

    const auth = useAuth();

    return (
        <div>
            <h1>Pulsar Manager</h1>
            <p>Hello {auth.state.user?.name}</p>
        </div>
  );
}

