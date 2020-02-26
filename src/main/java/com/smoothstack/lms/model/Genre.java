package com.smoothstack.lms.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "t_genre")
@Access(AccessType.FIELD)
public class Genre implements Serializable {

	@Id
	@Column(name = "genreId")
	@SequenceGenerator(name = "genre", sequenceName = "genreId", initialValue = 1, allocationSize = 1)
	@GeneratedValue(generator = "genre")
	private long genreId;

	@Column(name = "genreName", nullable = false)
	@NotNull
	@NotBlank
	private String genreName;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH }, fetch = FetchType.LAZY)
	@JoinTable(name = "r_genre_book", joinColumns = @JoinColumn(name = "genreId", referencedColumnName = "genreId"), inverseJoinColumns = @JoinColumn(name = "bookId", referencedColumnName = "bookId"))
	private Set<Book> genreBookSet = new HashSet<>();

	public Genre() {
	}

	public Genre(@NotNull String genreName) {
		this.genreName = genreName;
	}

	public long getGenreId() {
		return genreId;
	}

	public void setGenreId(long genreId) {
		this.genreId = genreId;
	}

	public String getGenreName() {
		return genreName;
	}

	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}

	public Set<Book> getGenreBookSet() {
		return genreBookSet;
	}

	public void setGenreBookSet(Set<Book> genreBookSet) {
		this.genreBookSet = genreBookSet;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Genre genre = (Genre) o;
		return getGenreId() == genre.getGenreId() && getGenreName().equals(genre.getGenreName())
				&& Objects.equals(getGenreBookSet(), genre.getGenreBookSet());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getGenreId(), getGenreName());
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", Genre.class.getSimpleName() + "[", "]").add("id=" + genreId)
				.add("name='" + genreName + "'").add("bookSet=" + genreBookSet.stream()
						.map(book -> Long.toString(book.getBookId())).collect(Collectors.joining(",", "[", "]")))
				.toString();
	}
}
