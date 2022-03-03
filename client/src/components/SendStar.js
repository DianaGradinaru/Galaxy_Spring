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
                message: text,
                sent_by_id: 1,
                image: "",
                created_at: "",
            }
        );

        if (response) {
            setStars([response, ...stars]);
            setText("");
            console.log(stars);
        }
    };

    return (
        <div className="mb-3">
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
                <div className="form-group text-start">
                    <input
                        type="file"
                        name="file"
                        accept=".jpg,.gif,.png"
                        className="form-control-file"
                        id="uploadImage"
                    />
                </div>
                <button type="submit" className="btnSendStar">
                    Send star
                </button>
            </form>
        </div>
    );
};

export default SendStar;
