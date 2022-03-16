import React, { useState } from "react";
import state from "../stateManager";
import { useAtom } from "jotai";
import api from "../fetch";

const SendStar = () => {
    const [text, setText] = useState("");
    const [stars, setStars] = useAtom(state.cardsAtom);

    const handlePost = async (e) => {
        e.preventDefault();

        const response = await api.post(
            "http://localhost:8080/api/v1/galaxies",
            {
                // created_at: "",
                image: "",
                message: text,
                user: 1,
            }
        );

        if (response) {
            setStars([response, ...stars]);
            setText("");
            console.log(stars);
        }
    };

    return (
        <div
            className="modal fade"
            id="exampleModal"
            tabIndex="-1"
            aria-labelledby="exampleModalLabel"
            aria-hidden="true"
        >
            <div className="modal-dialog">
                <div className="modal-content">
                    <div className="modal-body">
                        <form onSubmit={handlePost}>
                            <div className="form-group">
                                <textarea
                                    type="text"
                                    className="form-control mb-3"
                                    name="text"
                                    id="text"
                                    rows="4"
                                    maxLength={200}
                                    value={text}
                                    onChange={(e) => setText(e.target.value)}
                                    aria-describedby="starText"
                                    placeholder="What's on your mind?  "
                                />
                                {/* <small id="starTextHelp" className="form-text text-muted ">
                        {200 - text.length}
                    </small> */}
                            </div>
                            <div className="row">
                                <div className="col">
                                    <label
                                        htmlFor="file-upload"
                                        className="custom-file-upload"
                                    >
                                        Image
                                    </label>
                                </div>
                                {/* </div>
                            <div className="row"> */}
                                <div className="col">
                                    <input
                                        type="file"
                                        name="file"
                                        accept=".jpg,.gif,.png"
                                        className="form-control-file"
                                        id="file-upload"
                                    />
                                </div>
                                <div className="col ms-auto">
                                    <button
                                        type="submit"
                                        className="btnSendStar"
                                    >
                                        Send star
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default SendStar;
