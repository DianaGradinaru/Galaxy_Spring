import { getDownloadURL, ref, uploadBytesResumable } from "firebase/storage";
import { storage } from "../firebase";
import React, { useState } from "react";
import state from "../stateManager";
import { useAtom } from "jotai";
import api from "../fetch";

const SendStar = () => {
    const [text, setText] = useState("");
    const [stars, setStars] = useAtom(state.cardsAtom);
    const [imageUrl, setImageUrl] = useState("");

    const uploadFiles = (file, posting = false) => {
        if (!file) return;

        const random_string = Math.random().toString(36).substring(2, 7);
        const newName = file.name.replace(
            /(\.png|\.jpe?g|\.gif)/gim,
            random_string + "$1"
        );

        const storageRef = ref(storage, `/files/${newName}`);
        const uploadTask = uploadBytesResumable(storageRef, file);

        uploadTask.on(
            "state_changed",
            () => {},
            (err) => console.log(err),
            () => {
                getDownloadURL(uploadTask.snapshot.ref).then(async (url) => {
                    // setImageUrl(url);
                    if (posting) {
                        const response = await api.post(
                            process.env.REACT_APP_SERVER_ALL_GALAXIES,
                            {
                                image: url,
                                message: text,
                                user: 1,
                            }
                        );

                        if (response) {
                            setStars([response, ...stars]);
                            setText("");
                            setImageUrl("");
                            // console.log(stars);
                            document.getElementById("file-upload").value = "";
                        }
                    }
                });
            }
        );
    };

    const handlePost = async (e) => {
        e.preventDefault();

        let file = e.target.file.files[0];
        if (!file) {
            const response = await api.post(
                process.env.REACT_APP_SERVER_ALL_GALAXIES,
                {
                    image: "",
                    message: text,
                    user: 1,
                }
            );

            if (response) {
                setStars([response, ...stars]);
                setText("");
                setImageUrl("");
                // console.log(stars);
                // document.getElementById("file-upload").value = "";
                e.target.reset();
            }
        }
        console.log(e.target.file.files[0]);
        await uploadFiles(file, true);
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
                        <form id="star-form" onSubmit={handlePost}>
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
                                        accept=".jpg,.jpeg,.gif,.png"
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
