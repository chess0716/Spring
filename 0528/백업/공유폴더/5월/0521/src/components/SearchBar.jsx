import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import Search from '../utils/Search';
import '../styles/SearchBar.scss';

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSearch } from '@fortawesome/free-solid-svg-icons';

import store from '../store';

function SearchBar() {
	const { BoardStore } = store();
	const navigate = useNavigate();

	const [searchValue, setSearchValue] = useState('');
	const handleSearchValue = (e) => {
		setSearchValue(e.target.value);
	};

	return (
		<div className="searchBar">
			<input
				className="searchBar__input"
				placeholder="게시글 검색.."
				value={searchValue}
				onChange={handleSearchValue}
				onKeyDown={async (e) => {
					if (e.key === 'Enter') {
						if (searchValue === '') {
							alert('검색어를 입력해주세요!');
							return;
						}
						const searchResult = await Search(searchValue);
						if (searchResult.status === 404) {
							BoardStore.setSearchKeyword(searchValue);
							BoardStore.setSearchWritings([]);
							return;
						}
						BoardStore.setSearchKeyword(searchValue);
						BoardStore.setSearchWritings(searchResult);
						navigate('/page=1');
					}
				}}
			/>
			<div className="searchBar__icon">
				<FontAwesomeIcon
					icon={faSearch}
					onClick={async () => {
						if (searchValue === '') {
							alert('검색어를 입력해주세요!');
							return;
						}
						const searchResult = await Search(searchValue);
						if (searchResult.status === 404) {
							BoardStore.setSearchKeyword(searchValue);
							BoardStore.setSearchWritings([]);
							return;
						}
						BoardStore.setSearchKeyword(searchValue);
						BoardStore.setSearchWritings(searchResult);
						navigate('/page=1');
					}}
				/>
			</div>
		</div>
	);
}

export default SearchBar;
