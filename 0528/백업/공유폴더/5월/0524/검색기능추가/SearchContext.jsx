import React, { createContext, useState, useMemo } from 'react';

const SearchContext = createContext();

export function SearchProvider({ children }) {
	// 익명 함수에서 명시적 함수 선언으로 변경
	const [searchKeyword, setSearchKeyword] = useState('');

	// useMemo를 사용하여 컨텍스트 값 메모이제이션
	const contextValue = useMemo(() => ({ searchKeyword, setSearchKeyword }), [searchKeyword]);

	return <SearchContext.Provider value={contextValue}>{children}</SearchContext.Provider>;
}

export default SearchContext;
