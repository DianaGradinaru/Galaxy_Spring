import { useNavigate } from "react-router-dom";
import api from "../fetch";

const Login = () => {
    const navigate = useNavigate();

    const handleLogin = async (e) => {
        e.preventDefault();

        const req = await api.post("http://localhost:8080/login", {
            username: e.target.username.value,
            password: e.target.password.value,
        });

        if (req.ok) {
            const res = await req.json();
            navigate("/");
        }
    };

    return (
        <div className="form-control login-form">
            <div className="login-form-content">
                <div className="login-form-header">
                    <h5 id="loginmodaltitle" className="modal-title">
                        Login
                    </h5>
                </div>
                <div className="login-form-body">
                    <form onSubmit={handleLogin}>
                        <div className="mb-3">
                            <label htmlFor="username" className="form-label">
                                Your username
                            </label>
                            <input
                                type="text"
                                name="username"
                                className="form-control"
                                id="username"
                                aria-describedby="usernameHelp"
                                required
                            />
                        </div>
                        <div className="mb-3">
                            <label htmlFor="password" className="form-label">
                                Your password
                            </label>
                            <input
                                type="password"
                                name="password"
                                className="form-control"
                                id="password"
                                required
                            />
                        </div>

                        <button type="submit" className="btn btn-secondary">
                            Log in
                        </button>
                    </form>
                </div>
            </div>
        </div>
    );
};

export default Login;
