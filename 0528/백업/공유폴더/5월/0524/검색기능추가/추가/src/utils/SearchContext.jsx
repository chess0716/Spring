import React, { createContext, useState, useMemo } from 'react';

const SearchContext = createContext();

export const SearchProvider = ({ children }) => {
	const [searchKeyword, setSearchKeyword] = useState('');

	// useMemo를 사용하여 컨텍스트 값 메모이제이션
	const contextValue = useMemo(() => ({ searchKeyword, setSearchKeyword }), [searchKeyword]);

	return <SearchContext.Provider value={contextValue}>{children}</SearchContext.Provider>;
};

export default SearchContext;
