import { useNavigate } from "react-router-dom";
import api from "../fetch";

const Register = () => {
    const navigate = useNavigate();

    const handleRegister = async (e) => {
        e.preventDefault();

        // const formData = new FormData(e.target);
        console.log(e.target.email.password);

        const req = await api.post(
            "http://localhost:8080/api/v1/registration",
            {
                username: e.target.username.value,
                email: e.target.email.value,
                password: e.target.password.value,
            }
        );

        if (req.ok) {
            const res = await req.json();
            navigate("/login");
        }
    };

    return (
        <div className="modal-dialog">
            <div className="modal-content">
                <div className="modal-header">
                    <h5 className="modal-title">Register</h5>
                </div>
                <div className="modal-body">
                    <form onSubmit={handleRegister}>
                        <div className="mb-3">
                            <label htmlFor="username" className="form-label">
                                Username
                            </label>
                            <input
                                type="text"
                                className="form-control"
                                id="username"
                                name="username"
                                aria-describedby="usernameHelp"
                                required
                            />
                        </div>

                        <div className="mb-3">
                            <label htmlFor="email" className="form-label">
                                Your email address
                            </label>
                            <input
                                type="email"
                                className="form-control"
                                id="email"
                                name="email"
                                aria-describedby="emailHelp"
                                required
                            />
                            <div id="emailHelp" className="form-text">
                                We'll never share your email with anyone else.
                            </div>
                        </div>
                        <div className="mb-3">
                            <label htmlFor="password" className="form-label">
                                Create Password
                            </label>
                            <input
                                type="password"
                                className="form-control"
                                id="password"
                                required
                                name="password"
                            />
                        </div>

                        <button type="submit" className="btn btn-secondary">
                            Submit
                        </button>
                    </form>
                </div>
            </div>
        </div>
    );
};

export default Register;
