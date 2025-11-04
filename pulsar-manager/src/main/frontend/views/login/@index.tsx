import {useSignal} from "@vaadin/hilla-react-signals";
import {LoginOverlay} from "@vaadin/react-components";
import {useAuth} from "Frontend/auth";

export default function LoginView() {
    const { login } = useAuth();
    const hasError = useSignal(false);

    return (
        <LoginOverlay
            opened
            error={hasError.value}
            noForgotPassword
            onErrorChanged={(event) => {
                hasError.value = event.detail.value;
            }}
            onLogin={async ({ detail: { username, password } }) => {
                const { error } = await login(username, password);
                hasError.value = Boolean(error);
            }}
        />
    );
}